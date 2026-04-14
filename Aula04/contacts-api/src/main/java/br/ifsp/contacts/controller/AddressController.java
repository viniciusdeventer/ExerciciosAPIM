package br.ifsp.contacts.controller;

import br.ifsp.contacts.dto.AddressDTO;
import br.ifsp.contacts.mapper.ContactMapper;
import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
@Tag(name = "Addresses", description = "Gerenciamento de endereços dos contatos")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/{contactId}")
    @Operation(
        summary = "Adicionar endereço a um contato",
        description = "Cria um novo endereço vinculado a um contato existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public AddressDTO addAddressToContact(
            @PathVariable Long contactId,
            @RequestBody AddressDTO dto) {

        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + contactId));

        Address address = ContactMapper.toEntity(dto);
        address.setContact(contact);

        return ContactMapper.toDTO(addressRepository.save(address));
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os endereços",
        description = "Retorna uma lista paginada de endereços"
    )
    public Page<AddressDTO> getAllAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable)
                .map(ContactMapper::toDTO);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar endereço por ID",
        description = "Retorna um endereço específico pelo seu ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    public AddressDTO getAddressById(@PathVariable Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado: " + id));

        return ContactMapper.toDTO(address);
    }
}