package com.tomiok.itineraryservice.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CreateCityHttpRequest {

  @NotNull
  @NotEmpty
  private String code;

  @NotNull
  @NotEmpty
  private String name;
}
