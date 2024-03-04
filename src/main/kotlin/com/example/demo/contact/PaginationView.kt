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
        table.setColumns("id", "firstName", "lastName", "email", "company", "status")

        // Define results with a simpler data provider API, that just gives you page to
        // request
        table.setPagingDataProvider { page: Long, pageSize: Int ->
            // This is demo specific line, normally, e.g. with spring data, page number is
            // enough
            val start = (page * table.pageSize).toInt()
            // Optional, sorting
//            if (table.sortOrder.isNotEmpty()) {
//                val sortOrder: GridSortOrder<Contact> = table.sortOrder[0]
//                val propertyId = sortOrder.sorted.key
//                val asc = sortOrder.direction == SortDirection.ASCENDING
//                return@setPagingDataProvider service.findPersons(start, pageSize, propertyId, asc)
//            }
            service.findAll(PageRequest.of(start, pageSize)).content
        }
        table.setTotalResults(service.count())

        table.setPaginationBarMode(PaginationBarMode.BOTTOM)

        add(table)
    }
}
