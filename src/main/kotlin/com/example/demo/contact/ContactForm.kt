package com.example.demo.contact

import com.vaadin.flow.component.ComponentEvent
import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.EmailField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.BeanValidationBinder
import com.vaadin.flow.shared.Registration


class ContactForm(companies: List<Company>, statuses: List<Status>) : FormLayout() {
    private var firstName: TextField = TextField("First name")
    private var lastName: TextField = TextField("Last name")
    private var email: EmailField = EmailField("Email")
    private var status: ComboBox<Status> = ComboBox("Status")
    private var company: ComboBox<Company> = ComboBox("Company")
    private var binder: BeanValidationBinder<Contact> = BeanValidationBinder(Contact::class.java)


    private var save: Button = Button("Save")
    private var delete: Button = Button("Delete")
    private var close: Button = Button("Cancel")

    init {
        addClassName("contact-form")
        binder.bindInstanceFields(this)
        company.setItems(companies)
        company.setItemLabelGenerator(Company::name)
        status.setItems(statuses)
        status.setItemLabelGenerator(Status::name)



        add(
            firstName,
            lastName,
            email,
            company,
            status,
            createButtonsLayout()
        )
    }

    private fun validateAndSave() {
        if (binder.isValid) {
            fireEvent(SaveEvent(this, binder.bean))
        }
    }
    private fun createButtonsLayout(): HorizontalLayout {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR)
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY)

        save.addClickShortcut(Key.ENTER)
        close.addClickShortcut(Key.ESCAPE)

        save.addClickListener { validateAndSave() }
        delete.addClickListener {
            fireEvent(
                DeleteEvent(this, binder.bean)
            )
        }
        close.addClickListener {
            fireEvent(
                CloseEvent(this)
            )
        }

        binder.addStatusChangeListener { save.isEnabled = binder.isValid }
        return HorizontalLayout(save, delete, close)
    }

    fun setContact(contact: Contact?) {
        binder.bean = contact
    }
    abstract class ContactFormEvent protected constructor(source: ContactForm, val contact: Contact?) :
        ComponentEvent<ContactForm>(source, false)

    class SaveEvent internal constructor(source: ContactForm, contact: Contact?) : ContactFormEvent(source, contact)

    class DeleteEvent internal constructor(source: ContactForm, contact: Contact?) : ContactFormEvent(source, contact)

    class CloseEvent internal constructor(source: ContactForm) : ContactFormEvent(source, null)

    fun addDeleteListener(listener: ComponentEventListener<DeleteEvent>): Registration {
        return addListener(DeleteEvent::class.java, listener)
    }

    fun addSaveListener(listener: ComponentEventListener<SaveEvent>): Registration {
        return addListener(SaveEvent::class.java, listener)
    }

    fun addCloseListener(listener: ComponentEventListener<CloseEvent>): Registration {
        return addListener(CloseEvent::class.java, listener)
    }
}