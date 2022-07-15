package com.oneul.postcommandservice.infra.kafka;

public enum MessageType {
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    final String type;

    MessageType(String type){
        this.type = type;
    }
}
