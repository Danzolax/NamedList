package com.zolax.nameslist.data.repositories

import io.reactivex.rxjava3.core.Observable

class BaseRepositoryimpl : BaseRepository {
    override fun getNames(): Observable<String> {

        return Observable.fromArray(
            "Aaron",
            "Abraham",
            "Adam",
            "Adrian",
            "Aidan",
            "Alan",
            "Albert",
            "Alejandro",
            "Alex",
            "Alexander",
            "Alfred",
            "Andrew",
            "Angel",
            "Anthony",
            "Antonio",
            "Ashton",
            "Austin",
            "Gabriel",
            "Gavin",
            "Geoffrey",
            "George",
            "Gerld",
            "Gilbert",
            "Gordon",
            "Graham",
            "Gregory",
            "Kaitlyn",
            "Katelyn",
            "Katherine",
            "Kathryn",
            "Kayla",
            "Kaylee",
            "Kimberly",
            "Kylie",
            "Mabel",
            "Mackenzie",
            "Madeline",
            "Madison",
            "Makayla",
            "Margaret",
            "Maria",
            "Marisa",
            "Marjorie",
            "Mary",
            "Maya",
            "Megan",
            "Melanie",
            "Samantha",
            "Sandra",
            "Sara",
            "Sarah",
            "Savannah",
            "Sharon",
            "Sheila",
            "Shirley",
            "Sierra",
            "Sofia",
            "Sophia",
            "Stephanie",
            "Susan",
            "Sybil",
            "Sydney",
            "Sylvia",
            "Ralph",
            "Raymond",
            "Reginald",
            "Richard",
            "Robert",
            "Rodrigo",
            "Roger",
            "Ronald",
            "Ryan",
            "Samuel",
            "Sean",
            "Sebastian",
            "Seth",
            "Simon",
            "Stanley",
            "Steven",
            "Wallace",
            "Walter",
            "William",
            "Wyatt"
        )
    }
}