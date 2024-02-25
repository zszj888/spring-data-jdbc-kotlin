package com.example.demo

import com.example.demo.ContactForm.DeleteEvent
import com.example.demo.ContactForm.SaveEvent
import com.vaadin.flow.component.AbstractField
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.function.ValueProvider
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import java.util.function.Consumer

@Route(value = "")
@PageTitle("Contacts | Vaadin CRM")
class ListView(private val service: CrmService) : VerticalLayout() {
    private val grid = Grid(Contact::class.java)
    private val filterText: TextField = TextField()
    private var form: ContactForm? = null

    init {
        addClassName("list-view")
        setSizeFull()
        configureGrid()
        configureForm()

        add(toolbar, content)
        updateList()
    }

    private val content: Component
        get() {
            val content = HorizontalLayout(grid, form)
            content.setFlexGrow(2.0, grid)
            content.setFlexGrow(1.0, form)
            content.addClassNames("content")
            content.setSizeFull()
            return content
        }


    private val toolbar: HorizontalLayout
        get() {
            filterText.placeholder = "Filter by name..."
            filterText.isClearButtonVisible = true
            filterText.valueChangeMode = ValueChangeMode.LAZY
            filterText.addValueChangeListener { updateList() }

            val addContactButton = Button("Add contact")

            val toolbar = HorizontalLayout(filterText, addContactButton)
            toolbar.addClassName("toolbar")
            return toolbar
        }

    private fun configureForm() {
        form = ContactForm(service.findAllCompanies(), service.findAllStatuses())
        form!!.width = "25em"
        form!!.addSaveListener { event: SaveEvent? ->
            this.saveContact(
                event
            )
        } // <1>
        form!!.addDeleteListener { event: DeleteEvent? ->
            this.deleteContact(
                event
            )
        } // <2>
    }

    private fun saveContact(event: SaveEvent?) {
        service.saveContact(event!!.contact)
        updateList()
    }

    private fun deleteContact(event: DeleteEvent?) {
        service.deleteContact(event!!.contact!!)
        updateList()
    }

    private fun configureGrid() {
        grid.addClassNames("contact-grid")
        grid.setSizeFull()
        grid.setColumns("firstName", "lastName", "email")
        grid.addColumn(ValueProvider<Contact, Any> { contact: Contact -> contact.status!!.name }).setHeader("Status")
        grid.addColumn(ValueProvider<Contact, Any> { contact: Contact -> contact.company!!.name }).setHeader("Company")
        grid.columns.forEach(Consumer { col: Grid.Column<Contact?> ->
            col.setAutoWidth(
                true
            )
        })

    }

    private fun getToolbar(): Component {
        filterText.placeholder = "Filter by name..."
        filterText.isClearButtonVisible = true
        filterText.valueChangeMode = ValueChangeMode.LAZY
        filterText.addValueChangeListener { e: AbstractField.ComponentValueChangeEvent<TextField?, String?>? -> updateList() }

        val addContactButton = Button("Add contact")
        val toolbar = HorizontalLayout(filterText, addContactButton)
        toolbar.addClassName("toolbar")
        return toolbar
    }

    private fun updateList() {
        grid.setItems(service.findAllContacts(filterText.value))
    }
}