package com.yomasoftware.domain.usesCases

//import com.yomasoftware.domain.utils.MeetingProcesor
//import org.koin.core.component.KoinComponent
//import org.koin.core.component.inject
//
//class DownloadAllWorkbooksWeb : KoinComponent {
//
//    private val repositoryDB: WorkbookMonthsRepo by inject()
//
//    @OptIn(DelicateCoroutinesApi::class)
//    suspend operator fun invoke() =
//
//        GlobalScope.launch(
//            Dispatchers.Default
//                    + CoroutineName("UpdateWorkbooks")
//        ) {
//            val data = repositoryDB.downloadMonthsInfo()
//            data.let { source ->
//                source.forEach { monthDao ->
//                    repositoryDB.addMonth(
//                        MonthWorkbook.fromWeb(
//                            monthDao,
//                            MeetingProcesor.getDateMonth(monthDao.title)
//                        )
//                            .copy(
//                                title = MeetingProcesor.buildWorkbookTitle(monthDao.title),
//                                subtitle = "Descargar..."
//                            )
//
//                    )
//                }
//            }
//        }
//}
//
//
//class GetMonthsCaseUse : KoinComponent {
//
//    private val repositoryDB: WorkbookMonthsRepo by inject()
//
//    operator fun invoke(): Flow<List<MonthWorkbook>> =
//        repositoryDB.dataMonthsDB.flowOn(Dispatchers.IO + CoroutineName("dbFlow"))
//}
//
//class GetWeeksOfMonthCaseUse : KoinComponent {
//
//    private val repositoryDB: RepositoryDB by inject<RepositoryDBImp>()
//
//    suspend operator fun invoke(idMonth: Long): List<WeeklyWorkbookMeetings> =
//        withContext(Dispatchers.IO) {
//            repositoryDB.getWeeksOfMonth(idMonth).map { it.toWeeklyWorkbook() }.sortedBy { it.date }
//        }
//
//}
//
//class GetAssignamentsWeekCaseUse : KoinComponent {
//
//    private val repositoryDB: RepositoryDB by inject<RepositoryDBImp>()
//
//    operator fun invoke(idWeek: Long): Flow<WeeklyWorkbookMeetings> =
//        repositoryDB.getWeekAssigns(idWeek).transform {
//            val infoMeeting = it.first()
//            val groupList =
//                it.groupBy { value -> MeetingPart.valueOf(value.meeting_part) }.mapValues { group ->
//                    group.value.map { assign -> assign.toAssignamentsGUI() }
//                }
//            val newData = WeeklyWorkbookMeetings(
//                infoMeeting.idWeek,
//                LocalDate.parse(infoMeeting.date_week),
//                infoMeeting.title_meeting,
//                infoMeeting.weekly_reading,
//                infoMeeting.meeting_president ?: "",
//                infoMeeting.initial_prayer ?: "",
//                infoMeeting.final_prayer ?: "",
//                infoMeeting.songs_list.toList(),
//                groupList,
//            )
//            emit(newData)
//        }
//}
//
//class DownloadProgressMonthInfoCasueUse : KoinComponent {
//    private val repositoryDB: RepositoryDB by inject()
//
//    operator fun invoke(titleMonth: String, linkSource: String): Flow<Float> = flow {
//
//        val dataLinks = repositoryDB.getWeekLinksOfMonth(linkSource) //IO
//            .filter { it != linkSource && it.contains(TextValidators.ES.VALIDATOR_LINK_MEETING) }
//
//        val progressWeight = 1f / dataLinks.size
//        var notIsFirst = false
//        val dataWeekOfMonth = dataLinks.map {
//            if (notIsFirst) emit(progressWeight) else notIsFirst = true
//            repositoryDB.downloadWeekInfo(it)
//        }
//        emit(.5f)
//        val dateOfMonth = MeetingProcesor.getDateMonth(titleMonth)
//        val subtititle = MeetingProcesor.getFirstAndLastWeek(
//            dataWeekOfMonth.first().title,
//            dataWeekOfMonth.last().title,
//        )
//        dataWeekOfMonth.forEach { DownloadWeekOfWebCaseUse()(it, dateOfMonth) }
//
//
//    }.flowOn(Dispatchers.Default + CoroutineName(titleMonth))
//
//}
//
//class DownloadWeekOfWebCaseUse : KoinComponent {
//
//    private val repositoryDB: RepositoryDB by inject()
//
//
//    operator fun invoke(weekDao: WeekDao, dateWorkbookMonth: LocalDate) {
//
//        println("Agregando ${weekDao.title}")
//        val dateWorkbookWeek = MeetingProcesor
//            .getDateDay(weekDao.title, dateWorkbookMonth)
//
//        val dataAssignaments = weekDao.assignamentsList.map {
//            DownloadAssignOfWebCaseUse()(it, dateWorkbookWeek.toString())
//        }.toList()
//        val idWeek = repositoryDB.insertRegisterWeek(weekDao, dateWorkbookWeek, dateWorkbookMonth)
//        dataAssignaments.forEach { repositoryDB.insertRegisterAssign(it, idWeek) }
//
//    }
//
//
//}
//
//class DownloadAssignOfWebCaseUse : KoinComponent {
//
//    operator fun invoke(assignDao: AssignamentDao, dateMonth: String): AssignamentsWeekly {
//
//        try {
//            println("Agregando ${assignDao.title}")
//            val dateWeek = MeetingProcesor
//                .getDateDay(assignDao.title, LocalDate.parse(dateMonth))
//            return AssignamentsWeekly(
//                0L,
//                assignDao.title,
//                "",
//                "",
//                MeetingProcesor.getAssignamentPosition(assignDao.title),
//                GiveIn.MAIN_HALL,
//                MeetingPart.entries[assignDao.meetingPart - 1],
//                MeetingProcesor.getTypeSpeech(assignDao.title),
//                dateWeek,
//            )
//
//        } catch (e: Exception) {
//
//            throw Exception("Formato de Asignacion no reconocido")
//        }
//    }
//}
//
//
