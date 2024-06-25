package com.shopping.ecomart.dtos;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ArvindKumar
 * @param <M> MessageDTO
 * @param <R> Response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDTO<M, R> {

    private M message;
    private R response;

}
