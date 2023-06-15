package com.example.chatai.data.model

import androidx.annotation.StringRes
import com.example.chatai.R

data class ExploreOptions(var title: String, val optionDetails: List<OptionDetails>)

data class OptionDetails( /*@DrawableRes val imageid: Int ,*/ @StringRes val title: Int, @StringRes val details: Int)

data class ExploreInbox(var title: String, val InboxOptionList: List<String>)

object ExploreInboxOptions{



    val facebook = ExploreInbox("Facebook", listOf(
        "Generate relevant hashtags for increased visibility and engagement.",
        "Suggest optimal posting time for maximum reach and interaction.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary.",
        "Analyze comments sentiment and provide overall sentiment summary."
    )
    )

    val instagram = ExploreInbox("Instagram", listOf(
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?.",
        "How to write captivating Instagram captions for engagement?."
    )
    )

    val linkedin = ExploreInbox("LinkedIn", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val email = ExploreInbox("Email", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val twitter = ExploreInbox("Twitter", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val cpp = ExploreInbox("C++", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val java = ExploreInbox("Java", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val github = ExploreInbox("Github", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val html = ExploreInbox("HTML", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val oop = ExploreInbox("OOP", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val ml = ExploreInbox("Machine Learning", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val mental_health = ExploreInbox("Mental Health", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val nutrition = ExploreInbox("Nutrition", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val exercise = ExploreInbox("Exercise", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val sleep_health = ExploreInbox("Sleep health", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val interview_prep = ExploreInbox("Interview Prepration", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val resume_writing = ExploreInbox("Resume writing", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val questions = ExploreInbox("Questions", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val interview_mistakes = ExploreInbox("Interview Mistakes", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val dress_code = ExploreInbox("dress code", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val interview_tips = ExploreInbox("Interview Tips", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val mock_interview = ExploreInbox("Mock Interview", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val world_war = ExploreInbox("worlds War", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val cold_war = ExploreInbox("Cold War", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val islamic_history = ExploreInbox("Islamic history", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val american_history = ExploreInbox("American history", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val middle_ages = ExploreInbox("Middle Ages", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )
    val holocause = ExploreInbox("HoloCaust", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val ancient_ages = ExploreInbox("Ancient ages", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )
    val mediaval_europe = ExploreInbox("Medieval Europe", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val poetry = ExploreInbox("Poetry", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val blogging = ExploreInbox("Blogging", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val eassay_writing = ExploreInbox("Essay writing", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val story_telling = ExploreInbox("Storytelling", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )

    val article = ExploreInbox("Article", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )
    val paragraph = ExploreInbox("paragraph", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )
    val summarize = ExploreInbox("Summarize", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )
    val languages = ExploreInbox("languages", listOf(
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?",
        "How to optimize LinkedIn profile for increased visibility and engagement?"
    )
    )



    val OptionList: List<ExploreInbox> = listOf(facebook, instagram, linkedin, email, twitter, cpp, java, github, html, oop, ml, mental_health, nutrition,
    exercise, sleep_health, interview_prep, resume_writing, questions, interview_mistakes, dress_code, interview_tips, mock_interview, world_war, cold_war,
    islamic_history, american_history, middle_ages, holocause, ancient_ages, mediaval_europe, poetry, blogging, eassay_writing, story_telling, article, paragraph,
    summarize, languages)


}


object ExploreData{


    private val social_details = listOf(
        OptionDetails(R.string.email, R.string.email_detail),
        OptionDetails(R.string.linkdin, R.string.linkdin_detail),
        OptionDetails(R.string.facebook, R.string.facebook_detail),
        OptionDetails(R.string.instagram, R.string.instagram_detail),
        OptionDetails(R.string.twitter, R.string.twitter_detail)
    )

    val Social = ExploreOptions("Social Media", social_details)

    private val coding_details = listOf(
        OptionDetails(R.string.cpp, R.string.cpp_details),
        OptionDetails(R.string.java, R.string.java_details),
        OptionDetails(R.string.github, R.string.github_details),
        OptionDetails(R.string.html, R.string.html_details),
        OptionDetails(R.string.oop, R.string.oop_details),
        OptionDetails(R.string.ML, R.string.ML_details)
    )
    val coding = ExploreOptions("Coding", coding_details)

    private val health_details = listOf(
        OptionDetails(R.string.mental_health, R.string.mental_health_details),
        OptionDetails(R.string.nutrition, R.string.nutrition_details),
        OptionDetails(R.string.exercise, R.string.exercise_details),
        OptionDetails(R.string.sleep_health, R.string.sleep_health_details)
    )
    val health = ExploreOptions("Health", health_details)

    private val interview_details = listOf(
        OptionDetails(R.string.interview_prepration, R.string.interview_prepration_details),
        OptionDetails(R.string.resume_writing, R.string.resume_writing_details),
        OptionDetails(R.string.questions, R.string.questions_details),
        OptionDetails(R.string.interview_mistakes, R.string.interview_mistakes_details),
        OptionDetails(R.string.dress_code, R.string.dress_code_details),
        OptionDetails(R.string.interview_tips, R.string.interview_tips_details),
        OptionDetails(R.string.mock_interview, R.string.mock_interview_details)
    )
    val interview = ExploreOptions("Interview", interview_details)

    private val history_details = listOf(
        OptionDetails(R.string.world_war, R.string.world_war_details),
        OptionDetails(R.string.cold_war, R.string.cold_war_details),
        OptionDetails(R.string.islamic_history, R.string.islamic_history_details),
        OptionDetails(R.string.american_history, R.string.american_history_details),
        OptionDetails(R.string.middle_ages, R.string.middle_ages_details),
        OptionDetails(R.string.holocaust, R.string.holocaust_details),
        OptionDetails(R.string.ancient_ages, R.string.ancient_ages_details),
        OptionDetails(R.string.medieval_europe, R.string.medieval_europe_details)
    )
    val history = ExploreOptions("History" , history_details)

    private val writing_details = listOf(
        OptionDetails(R.string.poetry, R.string.poetry_details),
        OptionDetails(R.string.blogging, R.string.blogging_details),
        OptionDetails(R.string.essay_writing, R.string.essay_writing_details),
        OptionDetails(R.string.story_telling, R.string.story_telling_details),
        OptionDetails(R.string.article, R.string.article_details),
        OptionDetails(R.string.photograph, R.string.photograph_details),
        OptionDetails(R.string.summarize, R.string.summarize_details),
        OptionDetails(R.string.languages, R.string.languages_details)
    )
    val writing = ExploreOptions("Writing", writing_details)

}

