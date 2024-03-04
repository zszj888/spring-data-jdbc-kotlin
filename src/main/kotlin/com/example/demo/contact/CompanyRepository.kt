package com.example.demo.contact

import org.springframework.data.repository.ListCrudRepository

interface CompanyRepository : ListCrudRepository<Company, Long>