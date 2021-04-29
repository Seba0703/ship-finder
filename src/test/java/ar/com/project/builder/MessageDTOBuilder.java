package ar.com.project.builder;

import ar.com.project.dto.MessageDTO;

public class MessageDTOBuilder {

    public static MessageDTO build() {
        MessageDTO ms = new MessageDTO();
        ms.setMessage("HOla CCCSSS");
        return ms;
    }
}
