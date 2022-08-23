package com.digitaldot.controller;


import com.digitaldot.exceptions.ApiException;
import com.digitaldot.exceptions.ValidatorErrorException;
import com.digitaldot.model.dto.PageUserDto;
import com.digitaldot.model.dto.UserDto;
import com.digitaldot.service.interfaces.IUserService;
import com.digitaldot.service.interfaces.IValidator;
import com.digitaldot.utils.HeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractUserController {

    @Autowired
    private IUserService userService;
//    @Autowired
//    private IValidator validator;
    @Autowired
    private HeadersUtil headersUtil;

    @GetMapping("/findAll")
    public ResponseEntity<CollectionModel<UserDto>> listAll(@PageableDefault(size = 5) Pageable page) throws ApiException, ValidatorErrorException {

            PageUserDto pageUserDto = userService.listAll(page);
            HttpHeaders headers = headersUtil.getHeadersPage(String.valueOf(pageUserDto.getItens()),
                    String.valueOf(pageUserDto.getTotalItens()),
                    String.valueOf(pageUserDto.getTotalPages()));

        return ResponseEntity.ok().headers(headers).body(pageUserDto.getUsers());
    }

    @GetMapping("/find/query")
    public ResponseEntity<UserDto> findByQuery(@RequestParam(name = "value") String query)
            throws ApiException, ValidatorErrorException {

        return ResponseEntity.ok(userService.findByQuery(query));
    }
    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) throws ApiException, ValidatorErrorException {

//        if (validator.hasErros(userDto)) {
//            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto)
            throws ApiException, ValidatorErrorException {

//        if (validator.hasErros(userDto)) {
//            throw new ValidatorErrorException(validator.getAllErrors(), HttpStatus.BAD_REQUEST.value());
//        }
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
