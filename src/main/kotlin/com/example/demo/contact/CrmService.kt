package com.example.demo.contact

import org.springframework.stereotype.Service

@Service
class CrmService(
    private val contactRepository: ContactRepository,
    private val companyRepository: CompanyRepository,
    private val statusRepository: StatusRepository
) {
    fun findAllContacts(stringFilter: String?): List<Contact> {
        return if (stringFilter.isNullOrEmpty()) {
            contactRepository.findAll()
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
        return companyRepository.findAll()
    }

    fun findAllStatuses(): List<Status> {
        return statusRepository.findAll()
    }

}