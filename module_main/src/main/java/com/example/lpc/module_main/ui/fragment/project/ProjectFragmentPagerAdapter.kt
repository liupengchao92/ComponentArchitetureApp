package com.example.lpc.module_main.ui.fragment.project

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lpc.lib_common.http.pojo.ProjectTree

/**
 * Author: liupengchao
 * Date: 2022/4/21
 * ClassName :ProjectFragmentPagerAdapter
 * Desc:
 */
class ProjectFragmentPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val fragments = mutableListOf<Fragment>()

   /* var tree: MutableList<ProjectTree> = mutableListOf()
        set(value) {
            field = value

            field.forEach {
                fragments.add(ProjectListFragment.getInstance(it))
            }
            notifyDataSetChanged()
        }

*/

    fun setTrees(tree:MutableList<ProjectTree>){

        tree.forEach {
            fragments.add(ProjectListFragment.getInstance(it))
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}