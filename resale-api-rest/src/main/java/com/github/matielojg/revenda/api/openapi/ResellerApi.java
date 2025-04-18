package com.github.matielojg.revenda.api.openapi;

import com.github.matielojg.revenda.api.dto.RegisterResellerRequest;
import com.github.matielojg.revenda.api.dto.ResellerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Resellers", description = "Endpoints for managing resellers")
public interface ResellerApi {

    @Operation(summary = "Register a new reseller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseller created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<ResellerResponse> register(@Valid @RequestBody RegisterResellerRequest request);
}
