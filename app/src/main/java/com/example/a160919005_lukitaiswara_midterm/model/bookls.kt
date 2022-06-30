package com.example.a160919005_lukitaiswara_midterm.model

import android.app.Application

class bookLists : Application() {

    companion object {

        var globalBookLike = """
            {
                "ISBN":"1",
                "title":"Alexander Hamilton",
                "author":"Ron Chernow",
                "publisher":"Penguin Books",
                "photo":"https://cdn.waterstones.com/bookjackets/large/9781/8002/9781800244399.jpg",
                "rating":4.2,
                "description":"Few figures in American history have been more hotly debated or more grossly misunderstood than Alexander Hamilton. Chernows biography gives Hamilton his due and sets the record straight, deftly illustrating that the political and economic greatness of today’s America is the result of Hamilton’s countless sacrifices to champion ideas that were often wildly disputed during his time. Chernow here recounts Hamilton turbulent life: an illegitimate, largely self-taught orphan from the Caribbean, he came out of nowhere to take America by storm, rising to become George Washington aide de camp in the Continental Army, coauthoring The Federalist Papers, founding the Bank of New York, leading the Federalist Party, and becoming the first Treasury Secretary of the United States.Historians have long told the story of Americas birth as the triumph of Jefferson’s democratic ideals over the aristocratic intentions of Hamilton. Chernow presents an entirely different man, whose legendary ambitions were motivated not merely by self-interest but by passionate patriotism and a stubborn will to build the foundations of American prosperity and power. His is a Hamilton far more human than we’ve encountered before from his shame about his birth to his fiery aspirations, from his intimate relationships with childhood friends to his titanic feuds with Jefferson, Madison, Adams, Monroe, and Burr, and from his highly public affair with Maria Reynolds to his loving marriage to his loyal wife Eliza. And never before has there been a more vivid account of Hamilton’s famous and mysterious death in a duel with Aaron Burr in July of 1804.",
                "genres":"Biography",
                "date":"March 29th 2005",
                "qty": 5,
                "like": 0
            }"""
    }
}