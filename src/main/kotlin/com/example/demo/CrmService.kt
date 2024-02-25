package com.example.demo

import org.springframework.stereotype.Service

@Service
class CrmService(
    val contactRepository: ContactRepository,
//    val companyRepository: CompanyRepository,
//    val statusRepository: StatusRepository
) {

    fun findAllContacts(stringFilter: String?): List<Contact?> {
        return if (stringFilter.isNullOrEmpty()) {
            contactRepository.findAll().toList()
        } else {
            contactRepository.search(stringFilter)
        }
    }

    fun countContacts(): Long {
        return contactRepository.count()
    }

    fun deleteContact(contact: Contact) {
        contactRepository.delete(contact)
    }

    fun saveContact(contact: Contact?) {
        if (contact == null) {
            println("Contact is null. Are you sure you have connected your form to the application?")
            return
        }
        contactRepository.save(contact)
    }

    fun findAllCompanies(): List<Company> {
        return listOf()
//        return companyRepository.findAll()
    }

    fun findAllStatuses(): List<Status> {
        return listOf()
//        return statusRepository.findAll()
    }
}