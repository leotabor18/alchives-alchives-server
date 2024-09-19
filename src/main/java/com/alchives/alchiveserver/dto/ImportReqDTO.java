package com.alchives.alchiveserver.dto;

import java.util.List;

import lombok.Data;

@Data
public class ImportReqDTO {
  List<ImportDTO> importUsers;
}
