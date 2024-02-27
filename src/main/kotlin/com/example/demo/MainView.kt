package com.example.demo


import com.vaadin.flow.component.AbstractField
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route


@Route(value = "")
@PageTitle("Contacts | Vaadin CRM")
class ListView(private val service: CrmService) : VerticalLayout() {
    private val grid = Grid(Contact::class.java)
    private val filterText = TextField()
    private val form: ContactForm

    init {
        addClassName("list-view")
        setSizeFull()
        configureGrid()
        form = ContactForm(service.findAllCompanies(), service.findAllStatuses())
        configureForm()
        add(getToolbar(), getContent())
        updateList()
    }

    private fun updateList() {
        grid.setItems(service.findAllContacts(filterText.value))
    }

    private fun configureForm() {
        form.width = "25em"
    }

    private fun getContent(): Component {
        val content = HorizontalLayout(grid, form)
        content.setFlexGrow(2.0, grid)
        content.setFlexGrow(1.0, form)
        content.addClassNames("content")
        content.setSizeFull()
        return content
    }

    private fun getToolbar(): HorizontalLayout {
        filterText.placeholder = "Filter by name..."
        filterText.isClearButtonVisible = true
        filterText.valueChangeMode = ValueChangeMode.LAZY
        filterText.addValueChangeListener { updateList() }


        val addContactButton = Button("Add contact")

        val toolbar = HorizontalLayout(filterText, addContactButton)
        toolbar.addClassName("toolbar")
        return toolbar
    }

    private fun configureGrid() {
        grid.addClassNames("contact-grid")
        grid.setSizeFull()
        grid.setColumns("firstName", "lastName", "email")
        grid.addColumn({ it.status?.name?:"" }).setHeader("Status")
        grid.addColumn({ it.company?.name?:"" }).setHeader("Company")
        grid.columns.forEach { it.isAutoWidth = true }
    }

}
