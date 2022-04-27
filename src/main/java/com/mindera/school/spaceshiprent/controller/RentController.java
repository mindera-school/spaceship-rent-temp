package com.mindera.school.spaceshiprent.controller;

import com.mindera.school.spaceshiprent.dto.rent.CreateOrUpdateRentDto;
import com.mindera.school.spaceshiprent.dto.rent.RentDetailsDto;
import com.mindera.school.spaceshiprent.service.rent.RentService;
import com.mindera.school.spaceshiprent.util.LoggerHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RentController {

    public static final String PATH_CREATE_RENT = "/rents";
    public static final String PATH_GET_RENTS = "/rents";
    public static final String PATH_GET_RENT_BY_ID = "/rents/{id}";
    public static final String PATH_GET_RENT_BY_CUSTOMER_ID = "/customers/{customerId}/rents";
    public static final String PATH_GET_RENT_BY_SPACESHIP_ID = "/spaceships/{spaceshipId}/rents";
    public static final String PATH_UPDATE_RENT = "/rents/{id}";
    public static final String PATH_PICKUP_RENT = "/customers/{userId}/rents/{rentId}/pickup";
    public static final String PATH_RETURN_RENT = "/customers/{userId}/rents/{rentId}/return";

    private final RentService rentService;

    @PostMapping(PATH_CREATE_RENT)
    public ResponseEntity<RentDetailsDto> createRent(@RequestBody @Valid CreateOrUpdateRentDto dto) {
        log.info(LoggerHelper.POST_REQUEST, LoggerHelper.RENT);
        return ResponseEntity.ok(rentService.createRent(dto));
    }

    @GetMapping(PATH_GET_RENTS)
    public ResponseEntity<List<RentDetailsDto>> getAllRents() {
        log.info(LoggerHelper.GET_ALL_REQUEST, LoggerHelper.RENT);
        return ResponseEntity.ok(rentService.getAllRents());
    }

    @GetMapping(PATH_GET_RENT_BY_ID)
    public ResponseEntity<RentDetailsDto> getRentById(@PathVariable Long id) {
        log.info(LoggerHelper.GET_REQUEST, LoggerHelper.RENT);
        return ResponseEntity.ok(rentService.getRentById(id));
    }

    @GetMapping(PATH_GET_RENT_BY_CUSTOMER_ID)
    public ResponseEntity<List<RentDetailsDto>> getRentByCostumerId(@PathVariable Long customerId) {
        log.info(LoggerHelper.GET_REQUEST, "rents by customer id");
        return ResponseEntity.ok(rentService.getRentsByCustomerId(customerId));
    }

    @GetMapping(PATH_GET_RENT_BY_SPACESHIP_ID)
    public ResponseEntity<List<RentDetailsDto>> getRentBySpaceshipId(@PathVariable Long spaceshipId) {
        log.info(LoggerHelper.GET_REQUEST, "rents by user id");
        return ResponseEntity.ok(rentService.getRentsBySpaceshipId(spaceshipId));
    }

    @PutMapping(PATH_UPDATE_RENT)
    public ResponseEntity<RentDetailsDto> updateRentById(
            @PathVariable Long id,
            @RequestBody @Valid CreateOrUpdateRentDto dto) {
        log.info(LoggerHelper.PUT_REQUEST, LoggerHelper.RENT);
        return ResponseEntity.ok(rentService.updateRent(id, dto));
    }

    @PatchMapping(PATH_PICKUP_RENT)
    public ResponseEntity<RentDetailsDto> pickupRent(@PathVariable Long userId,
                                                     @PathVariable Long rentId) {
        log.info(LoggerHelper.PATCH_REQUEST, LoggerHelper.RENT_PICKUP);
        return ResponseEntity.ok(rentService.pickupRent(userId, rentId));
    }

    @PatchMapping(PATH_RETURN_RENT)
    public ResponseEntity<RentDetailsDto> returnRent(@PathVariable Long userId,
                                                     @PathVariable Long rentId) {
        log.info(LoggerHelper.PATCH_REQUEST, LoggerHelper.RENT_PICKUP);
        return ResponseEntity.ok(rentService.returnRent(userId, rentId));
    }
}



