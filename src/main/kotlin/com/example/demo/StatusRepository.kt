package com.example.demo

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository
import org.vaadin.crudui.crud.CrudListener
import java.util.*

interface StatusRepository : ListCrudRepository<Status, Long>