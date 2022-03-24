package com.example.lpc.lib_init

import com.example.lpc.annotation.InitTask
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

/**
 * 注解处理器
 *
 * */
class TaskProcessor : AbstractProcessor() {

    private lateinit var elementUtils: Elements

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)

        processingEnv?.let {
            elementUtils = it.elementUtils;
            //设置日志
            Log.setLogger(it.messager)
            Log.i("TaskProcessor===============>>init")

        }

    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?
    ): Boolean {
        Log.i("TaskProcessor===============>>process")
        roundEnv?.run {
            var elements = getElementsAnnotatedWith(InitTask::class.java)
         
            Log.i("[InitTask] size ${elements.size}")

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