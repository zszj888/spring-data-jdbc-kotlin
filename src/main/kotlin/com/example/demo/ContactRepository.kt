package com.example.demo

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository

interface ContactRepository : ListCrudRepository<Contact, Long> {
    @Query(
        """
           select c.id,c.first_name,c.last_name,c.email,s.id status_id,s.name status_name,co.id company_id, co.name company_name from contact c,status s,company 
           co where c.id = s.contact and c.id = co.contact and 
           lower(c.first_name) like lower(concat('%', :searchTerm, '%')) 
                or lower(c.last_name) like lower(concat('%', :searchTerm, '%')) 
        """
    )
    fun search(searchTerm: String): List<Contact>

}