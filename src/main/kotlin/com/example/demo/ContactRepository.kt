package com.example.demo

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.query.Param

interface ContactRepository : ListCrudRepository<Contact?, Long?> {
    @Query(
        """
           select c from contact c 
                where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) 
                or lower(c.lastName) like lower(concat('%', :searchTerm, '%')) 
        """
    )
    fun search(searchTerm: String?): List<Contact?>
}