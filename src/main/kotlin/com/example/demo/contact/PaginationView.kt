package com.example.demo.contact


import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import org.springframework.data.domain.PageRequest
import org.vaadin.firitin.components.grid.PagingGrid
import org.vaadin.firitin.components.grid.PagingGrid.PaginationBarMode


@Route(value = "/page")
@PageTitle("Contacts | Vaadin CRM")
class PaginationView(private val service: ContactRepository) : VerticalLayout() {
    init {
        val table = PagingGrid(Contact::class.java)
        table.setColumns("id", "firstName", "lastName", "email",  "company", "status")
        table.addColumn ({ contact: Contact -> contact.company!!.name },)
        table.addColumn ({ contact: Contact -> contact.status!!.name },)
        table.setPagingDataProvider { page: Long, pageSize: Int ->
            val start = (page * table.pageSize).toInt()
            service.findAll(PageRequest.of(start, pageSize)).content
        }
        table.setTotalResults(service.count())
        add(table)
    }

}
