package com.digitaldot.user.controller.v1;

import com.digitaldot.user.controller.AbstractUserController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractUserController {
}
