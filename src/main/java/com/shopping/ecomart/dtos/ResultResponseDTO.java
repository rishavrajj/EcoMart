/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.merkur.helper.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ArvindKumar
 * @param <M> MessageDTO
 * @param <R> Response
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultResponseDTO<M, R> {

    private M message;
    private R response;

    public ResultResponseDTO() {
        //Do not change anything here
    }

    public ResultResponseDTO(M message, R response) {
        this.message = message;
        this.response = response;
    }

    public M getMessage() {
        return message;
    }

    public void setMessage(M message) {
        this.message = message;
    }

    public R getResponse() {
        return response;
    }

    public void setResponse(R response) {
        this.response = response;
    }

}
