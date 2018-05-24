package com.hmelizarraraz.apicrudclient.presenter.interfaces;

/**
 * Interfaz encargada de enviar los datos al interactor
 */
public interface IAddUserPresenter {

    /**
     * Realiza una llamada al interactor enviando los datos de la vista
     * @param name Nombre de usuario obtenido del formulario de registro
     * @param email Email de usuario obtenido del formulario de registro
     * @param password Password de usuario obtenido del formulario de registro
     * @param password_confirmation Confirmación de contraseña obtenido del formulario de registro
     * @param gender Género de usuario obtenido del formulario de registro
     */
    void validateRegister(String name, String email, String password, String password_confirmation, String gender);
}
