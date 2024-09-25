package com.yomasoftware.data.server

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.network.parseGetRequestBlocking
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.select.Elements
import com.yomasoftware.data.interfaces.RemoteSource
import com.yomasoftware.data.models.AssignmentDao
import com.yomasoftware.data.models.MonthDao
import com.yomasoftware.data.models.WeekDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ScrappingWebImpl : RemoteSource {


    private suspend fun getPage(pageLink: String): Document =
        withContext(Dispatchers.IO) {
        val validatePage = validatePageJW(pageLink)
        Ksoup.parseGetRequestBlocking(validatePage)

    }

    override suspend fun donwloadWorkbookWeek(linkWeek: String): WeekDao = scrappingWeekInfo(linkWeek)

    override suspend fun getUpdateWoorkbooks(): List<MonthDao> {
        val sourceDoc: Element = getPage(JWVariables.SOURCE_JW_WORKBOOKS)
            .getElementById(JWVariables.ID_WORKBOOK)!!
        return scrappingMonthFlow(sourceDoc)
    }


    private fun scrappingMonthFlow(srcElement: Element): List<MonthDao> {

        val elementsOfWoorkBookPage = srcElement.getElementsByClass(JWVariables.CLASS_WORKBOOKS)
        val pagesData = mutableListOf<MonthDao>()

        elementsOfWoorkBookPage.forEach {
            val data = it.getElementsByAttribute("href")
            val linkPage = data.attr("href")
            if (linkPage.contains(JWVariables.FILTER_LINKS_WORKBOOKS)) {
                return@forEach
            }
            pagesData.add(MonthDao(data.text(), linkPage, listOf()))
        }
        return pagesData
    }

    override suspend fun getInfoMonthWorkbook(monthLink: String): List<WeekDao> {
        return scrappingWeekLinks(monthLink).map {
            scrappingWeekInfo(it)
        }
    }

    override suspend fun getLinksWeeksOfMonth(link: String): List<String> = scrappingWeekLinks(link)

    private suspend fun scrappingWeekLinks(monthLink: String): List<String> {
        val srcElement: Element = getPage(monthLink)
            .getElementById(JWVariables.ID_MONTHWORKBOOK)!!
        val elementsOfMonthWeb = srcElement.getElementsByClass(JWVariables.CLASS_MONTHWORKBOOK)

        return elementsOfMonthWeb.map {
            it.getElementsByAttribute("href").attr("href")
        }
    }

    private suspend fun scrappingWeekInfo(weekLink: String): WeekDao {
        val sourceDoc: Element = getPage(weekLink)
        return WeekDao(
            sourceDoc.getElementById("p1")!!.text(),
            weekLink,
            sourceDoc.getElementById("p2")!!.text(),
            getWeeklySongs(sourceDoc),
            scrappingWeeklyAssignaments(sourceDoc)
        )
    }


    private fun validatePageJW(pageLink: String): String {
        if (!pageLink.contains(JWVariables.JW_LINK)) {
            return JWVariables.JW_LINK + pageLink
        }
        return pageLink
    }

    private fun scrapTitleWoorkbook(monthlyDoc: Element): String =
        monthlyDoc.getElementsByTag("h1").text().removePrefix(JWVariables.GUIATITLE)

    private fun getWeeklyPortionBible(weeklyMeetingDoc: Document): String {
        val elements: Elements = weeklyMeetingDoc.getElementsByClass(JWVariables.CLASS_BIBLE)
        val speechesTreasures: MutableSet<String> = mutableSetOf()
        elements.forEach {
            it.getElementsByTag("strong").forEach {
                speechesTreasures.add(it.text())
            }
        }
        return speechesTreasures.first()
    }

    private fun scrappingWeeklyAssignaments(sourceELement: Element): List<AssignmentDao> {

        val elements: Elements = sourceELement.getElementsByTag("h3")
        val weeklyAssignaments: MutableList<AssignmentDao> = mutableListOf()
        val weeklyComments: List<String> = getWeeklyAsignamentsComments(sourceELement)

        var indexValue = 0
        elements.forEach loop@{

            if (it.hasClass("du-fontSize--basePlus2")) {
                return@loop
            }

            if (it.hasClass(JWVariables.COLOR_TREASURES)) {
                weeklyAssignaments.add(AssignmentDao(it.text(), 1, weeklyComments[indexValue]))
                indexValue++
            } else if (it.hasClass(JWVariables.COLOR_MINISTRY)) {
                weeklyAssignaments.add(AssignmentDao(it.text(), 2, weeklyComments[indexValue]))
                indexValue++
            } else if (it.hasClass(JWVariables.COLOR_LIVING)) {
                weeklyAssignaments.add(AssignmentDao(it.text(), 3, weeklyComments[indexValue]))
                indexValue++
            }
        }

        return weeklyAssignaments
    }

    private fun getWeeklySongs(weeklyDoc: Element): Set<String> {

        val elements: Elements = weeklyDoc.getElementsByClass(JWVariables.CLASS_SONGS)
        val setSongs: MutableSet<String> = mutableSetOf()
        elements.forEach {
            setSongs.add(it.text())
        }
        return setSongs
    }

    private fun getWeeklyAsignamentsComments(weeklyDoc: Element): List<String> {

        val elements: Elements = weeklyDoc.getElementsByClass(JWVariables.CLASS_TIME1)
        val timesList = mutableListOf<String>()
        elements.forEach {
            if (it.hasClass(JWVariables.CLASS_TIME2)) {
                timesList.add(it.text())
            }
        }
        return timesList
    }

    private object JWVariables {
        const val SOURCE_JW_WORKBOOKS: String =
            "https://www.jw.org/es/biblioteca/guia-actividades-reunion-testigos-jehova/"
        const val ID_WORKBOOK = "pubsViewResults"
        const val CLASS_WORKBOOKS = "publicationDesc"
        const val FILTER_LINKS_WORKBOOKS = "mwbr"
        const val ID_IMAGE_WOORKBOOK = "articleTopRelatedImage"
        const val ID_MONTHWORKBOOK = "article"
        const val CLASS_MONTHWORKBOOK = "syn-body"
        const val ID_WEEKTITLE = "p1"
        const val ID_WEEKBIBLE = "p2"
        const val GUIATITLE = "GUÍA DE ACTIVIDADES "
        const val CLASS_TIME2: String = "du-margin-top--1"
        const val CLASS_TIME1: String = "du-color--textSubdued"
        const val JW_LINK = "https://www.jw.org"
        const val CLASS_BIBLE = "jsBibleLink"
        const val COLOR_TREASURES = "du-color--teal-700"
        const val COLOR_MINISTRY = "du-color--gold-700"
        const val COLOR_LIVING = "du-color--maroon-600"
        const val CLASS_SONGS = "pub-sjj"

    }

}