package com.example.lpc.compiler

import com.example.lpc.annotation.InitTask
import com.example.lpc.annotation.ModuleTaskRegister
import com.example.lpc.annotation.TaskInfo
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

/**
 * 注解处理器
 *
 * */
class TaskProcessor : AbstractProcessor() {

    private lateinit var elementUtils: Elements

    private lateinit var typeUtils: Types

    private lateinit var moduleName: String

    private lateinit var filer: Filer

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)

        processingEnv?.let {
            elementUtils = it.elementUtils;
            typeUtils = it.typeUtils
            this.filer = it.filer
            //设置日志
            Log.setLogger(it.messager)
            Log.i("TaskProcessor===============>>init")


            val moduleName = processingEnv.options["moduleName"]
            if (moduleName == null || moduleName.isEmpty()) {
                throw IllegalArgumentException(
                    "[InitTask] Can not find apt argument 'moduleName', check if has add the code like this in module's build.gradle:\n" +
                            "    In Kotlin:\n" +
                            "    \n" +
                            "    kapt {\n" +
                            "        arguments {\n" +
                            "          arg(\"moduleName\", project.name)\n" +
                            "        }\n" +
                            "    }\n"
                )
            }

            this.moduleName = ProcessorUtils.formatModuleName(moduleName)

            Log.i("[InitTask] Start to deal module ${this.moduleName}")
        }

    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        roundEnv?.run {
            // 得到所有包含该注解的element集合
            var taskElements = getElementsAnnotatedWith(InitTask::class.java)

            Log.i("[InitTask] size ${taskElements.size}")

            if (taskElements.isNullOrEmpty()) return false


            var typeElement = elementUtils.getTypeElement("com.example.lpc.annotation.ITask")

            val parameterType =
                ClassName(
                    "kotlin.collections",
                    "MutableList"
                ).parameterizedBy(TaskInfo::class.asTypeName())

            val parameterSpec =
                ParameterSpec.builder(ProcessorUtils.PARAM_NAME, parameterType).build()

            val methodSpec = FunSpec.builder(ProcessorUtils.METHOD_NAME)
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(parameterSpec)


            taskElements.forEach { element ->
                //获取元素上的InitTask注解
                var task = element.getAnnotation(InitTask::class.java)
                //获取元素的类型
                var typeMirror = element.asType()
                //判断类型
                if (typeUtils.isSubtype(typeMirror, typeElement.asType())) {

                    var taskClassName = (element as TypeElement).asClassName()

                    methodSpec.addStatement(
                        "%N.add(%T(%S,%L,%T()))",
                        ProcessorUtils.PARAM_NAME,
                        TaskInfo::class.java,
                        task.name,
                        task.background,
                        taskClassName
                    )
                }
            }


            /**
             * Write to file
             */
            FileSpec.builder(ProcessorUtils.PACKAGE_NAME, "TaskRegister$$moduleName")
                .addType(
                    TypeSpec.classBuilder("TaskRegister")
                        .addKdoc(ProcessorUtils.JAVADOC)
                        .addSuperinterface(ModuleTaskRegister::class.java)
                        .addFunction(methodSpec.build())
                        .build()
                )
                .build()
                .writeTo(filer)
        }

        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val annotationTypes = mutableSetOf<String>()
        annotationTypes.add(InitTask::class.java.canonicalName)
        return annotationTypes
    }


    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
}