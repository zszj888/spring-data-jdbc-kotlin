package com.example.demo

import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import org.vaadin.crudui.crud.impl.GridCrud
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout



@Route
class MainView(repository: ContactRepository) : VerticalLayout() {
    init {
        setSizeFull()
        val crud = GridCrud(Contact::class.java, HorizontalSplitCrudLayout())
        crud.setFindAllOperation(repository::findAll)
        crud.setAddOperation(repository::save)
        crud.setUpdateOperation(repository::save)
        crud.setDeleteOperation(repository::delete)
        add(crud)
    }

}