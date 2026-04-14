package br.ifsp.contacts.mapper;

import br.ifsp.contacts.dto.*;
import br.ifsp.contacts.model.*;

import java.util.stream.Collectors;

public class ContactMapper {

    public static ContactDTO toDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();

        dto.setId(contact.getId());
        dto.setNome(contact.getNome());
        dto.setTelefone(contact.getTelefone());
        dto.setEmail(contact.getEmail());

        if (contact.getAddresses() != null) {
            dto.setAddresses(
                contact.getAddresses()
                        .stream()
                        .map(ContactMapper::toDTO)
                        .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();

        dto.setId(address.getId());
        dto.setRua(address.getRua());
        dto.setCidade(address.getCidade());
        dto.setEstado(address.getEstado());
        dto.setCep(address.getCep());

        return dto;
    }

    public static Contact toEntity(ContactDTO dto) {
        Contact contact = new Contact();

        contact.setNome(dto.getNome());
        contact.setTelefone(dto.getTelefone());
        contact.setEmail(dto.getEmail());

        return contact;
    }

    public static Address toEntity(AddressDTO dto) {
        Address address = new Address();

        address.setRua(dto.getRua());
        address.setCidade(dto.getCidade());
        address.setEstado(dto.getEstado());
        address.setCep(dto.getCep());

        return address;
    }
}