package br.ifsp.contacts.controller;

import br.ifsp.contacts.dto.AddressDTO;
import br.ifsp.contacts.dto.ContactDTO;
import br.ifsp.contacts.mapper.ContactMapper;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contacts", description = "Gerenciamento de contatos")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    @Operation(summary = "Listar contatos", description = "Retorna todos os contatos de forma paginada")
    public Page<ContactDTO> getAllContacts(Pageable pageable) {
        return contactRepository.findAll(pageable)
                .map(ContactMapper::toDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contato por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato encontrado"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ContactDTO getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));
        return ContactMapper.toDTO(contact);
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar contatos por nome", description = "Busca contatos pelo nome (parcial)")
    public List<ContactDTO> getContactsByName(@RequestParam("nome") String nome) {
        return contactRepository.findByNomeContaining(nome)
                .stream()
                .map(ContactMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}/addresses")
    @Operation(summary = "Listar endereços de um contato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereços retornados"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public List<AddressDTO> getAddressesByContact(@PathVariable Long id) {
        return addressRepository.findByContactId(id)
                .stream()
                .map(ContactMapper::toDTO)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Criar contato")
    @ApiResponse(responseCode = "200", description = "Contato criado com sucesso")
    public ContactDTO createContact(@RequestBody ContactDTO dto) {
        Contact contact = ContactMapper.toEntity(dto);
        return ContactMapper.toDTO(contactRepository.save(contact));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contato (completo)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato atualizado"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ContactDTO updateContact(@PathVariable Long id, @RequestBody ContactDTO dto) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

        existing.setNome(dto.getNome());
        existing.setTelefone(dto.getTelefone());
        existing.setEmail(dto.getEmail());

        return ContactMapper.toDTO(contactRepository.save(existing));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar contato (parcial)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato atualizado parcialmente"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ContactDTO updateContactFields(@PathVariable Long id, @RequestBody ContactDTO dto) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

        if (dto.getNome() != null)
            existing.setNome(dto.getNome());

        if (dto.getTelefone() != null)
            existing.setTelefone(dto.getTelefone());

        if (dto.getEmail() != null)
            existing.setEmail(dto.getEmail());

        return ContactMapper.toDTO(contactRepository.save(existing));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir contato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato removido"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
}