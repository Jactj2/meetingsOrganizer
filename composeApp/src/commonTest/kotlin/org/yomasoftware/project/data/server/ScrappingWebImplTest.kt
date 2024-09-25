package org.yomasoftware.project.data.server

import kotlinx.coroutines.test.runTest
import kotlin.test.Test


class ScrappingWebImplTest {

    private val scrappingWeb = ScrappingWebImpl()

    @Test
    fun getLinksMonth() {

    }

    @Test
    fun getFlowTest()= runTest {
        val dataFlow = scrappingWeb.getUpdateWoorkbooks()
        println("ya casi")
        dataFlow.collect{ month ->
            println(month.title)
            println(month.linkPage)
            println(month.linkImage)
        }
    }
}