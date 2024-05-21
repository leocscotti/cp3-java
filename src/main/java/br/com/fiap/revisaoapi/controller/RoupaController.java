package br.com.fiap.revisaoapi.controller;

import br.com.fiap.revisaoapi.dto.RoupaDTO;
import br.com.fiap.revisaoapi.model.Roupa;
import br.com.fiap.revisaoapi.service.RoupaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/roupas", produces = {"application/json"})
@Tag(name = "api-roupa")
public class RoupaController {
    private final RoupaService roupaService;

    @Autowired
    public RoupaController(RoupaService roupaService) {
        this.roupaService = roupaService;
    }

    @Operation(summary = "Retorna todas as roupas em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma roupa encontrada", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<RoupaDTO>> findAll() {
        Page<RoupaDTO> roupasDTO = roupaService.findAll();
        if (roupasDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (RoupaDTO roupaDTO : roupasDTO){
                Long id = roupaDTO.getId();
                roupaDTO.add(linkTo(methodOn(RoupaController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(roupasDTO);
    }

    @Operation(summary = "Retorna uma roupa específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma roupa encontrada para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoupaDTO> findById(@PathVariable Long id) {
        RoupaDTO roupaDTO = roupaService.findById(id);
        if (roupaDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            roupaDTO.add(linkTo(methodOn(RoupaController.class)
                    .findAll()).withRel("Lista de Roupas"));
        }
        return ResponseEntity.ok(roupaDTO);
    }

    @Operation(summary = "Grava uma roupa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Roupa gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Roupa> save(@Valid @RequestBody Roupa roupa) {
        Roupa roupaSalvo = roupaService.save(roupa);
        return new ResponseEntity<>(roupaSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza uma roupa com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Roupa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Roupa> update(@PathVariable Long id, @Valid @RequestBody Roupa roupa) {
        Roupa roupaSalvo = roupaService.update(id, roupa);
        return new ResponseEntity<>(roupaSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui uma roupa com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Roupa excluída com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roupaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
