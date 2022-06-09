package com.example.lesson.entity

data class Lesson  constructor(var date:String,var content: String,var state:State){

    enum class State{
        PLAYBACK {
            override fun toString(): String {
                return  "有回放"
            }

        },
        LIVE {
            override fun toString(): String {
                return "正在直播"
            }
        },
        WAIT{
            override fun toString(): String {
                return "等待中"
            }
        };

        public abstract override fun  toString():String
    }



}