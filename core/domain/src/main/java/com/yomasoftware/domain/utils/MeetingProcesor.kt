package com.yomasoftware.domain.utils

import kotlinx.datetime.LocalDate

class MeetingProcesor {

    companion object {

        fun buildWorkbookTitle ( daoTitle : String)  =
            daoTitle.replaceAfter(" de","").replace(" de","")

        fun getAssignamentPosition (assignamentTitle:String) : Int {
            return assignamentTitle.filter { it.isDigit() }.toInt()
        }

        fun getAssignamentTitle (assignamentTitle:String) : String {
            return assignamentTitle.filter { it.isLetter()|| it.isWhitespace() }.removeRange(0,1)
        }

        fun getTimeComments(assignamentTitle: String) : Int {
           return getFirstDigit(assignamentTitle, ".) ")
        }

        fun getDateMonth(tittleMonth :  String ) : LocalDate {
            val year = getAssignamentPosition(tittleMonth)
            val months = YearMonths.entries.filter { tittleMonth.uppercase().contains(it.name) }
            return LocalDate(year, (months[0].ordinal+1),1)
        }

        fun getDateDay(titleWeek:String, dateMonth: LocalDate) : LocalDate {
            val day = getFirstDigit( titleWeek," de" )
            val month = YearMonths.entries.filter { titleWeek.uppercase().contains(it.name) }
            val date = LocalDate(dateMonth.year,month.first().ordinal+1,day)
            return date
        }

        private fun getFirstDigit(rawData:String,delimiter:String) : Int{
            val filter = rawData.replaceAfter(delimiter,"").replaceAfter("-","")
            return filter.filter { it.isDigit() }.toInt()
        }

//        fun getTypeSpeech ( titleSpeech:String ) : TypePart {
//            val requestCompare = titleSpeech.uppercase()
//            if(requestCompare.contains("Empiece conversaciones".uppercase())){
//                return TypePart.STAGGIN1
//            }
//            else if(requestCompare.contains("Haga revisitas".uppercase()) ||
//                requestCompare.contains("Haga discípulos".uppercase())
//                ){
//                return TypePart.STAGGIN2
//            }
//            else if(requestCompare.contains("Explique sus creencias".uppercase())){
//                return TypePart.STAGGIN3
//            }
//            else if(requestCompare.contains("Estudio bíblico de la congregación".uppercase())){
//                return TypePart.STUDY_BOOK
//            }
//
//            return TypePart.SPEECH
//        }

        fun getFirstAndLastWeek ( firstTitle : String , lastTitle: String) : String {
            val firstDay = firstTitle.replaceAfter("-","").replace("-","")
            val firstMonth = firstTitle.replaceBefore(" de","")
            val lastBase = lastTitle.replaceBefore("a ","")
            val lastDay = lastTitle.replaceBefore("a ","")
            val lastMonth = lastTitle.replaceBefore(" de","")

            return " Semanas del $firstDay $firstMonth al $lastDay $lastMonth"
        }
    }
}

enum class YearMonths {
    ENERO,
    FEBRERO,
    MARZO,
    ABRIL,
    MAYO,
    JUNIO,
    JULIO,
    AGOSTO,
    SEPTIEMBRE,
    OCTUBRE,
    NOVIEMBRE,
    DICIEMBRE
}