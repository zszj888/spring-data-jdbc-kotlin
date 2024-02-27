package com.example.demo

import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.EmailField
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.BeanValidationBinder



class ContactForm(companies: List<Company?>, statuses: List<Status?>) : FormLayout() {
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
        binder.bindInstanceFields(this);
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

    private fun createButtonsLayout(): HorizontalLayout {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR)
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY)

        save.addClickShortcut(Key.ENTER)
        close.addClickShortcut(Key.ESCAPE)

        return HorizontalLayout(save, delete, close)
    }
}