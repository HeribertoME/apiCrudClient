package com.hmelizarraraz.apicrudclient.view.interfaces;

import android.content.Context;

/**
 * Interfaz de vista para registrar un usuario nuevo. Se definen los métodos correspondintes exclusivamente de la vista
 */
public interface IAddUserView {

    /**
     * Muestra error en el textInputLayout cuando el campo nombre está vacio
     */
    void showNameError();

    /**
     * Muestra error en el textInputLayout cuando el campo de email está vacio o no es un email válido
     */
    void showEmailError();

    /**
     * Muestra error en el textInputLayout cuando no se ha ingresado un password en el campo
     */
    void showPasswordError();

    /**
     * Muestra error en el textInputLayout cuando la confirmación de contraseña no coincide
     */
    void showPasswordConfirmationError();

    /**
     * Muestra un toast con mensaje de error en general
     * @param error El mensaje de error a mostrar
     * @param context Contexto de la aplicación
     */
    void showRegisterError(String error, Context context);

    /**
     * Muestra el indicador de carga
     * @param show True: Para mostrarlo. False: Ocultarlo
     */
    void showLoadingIndicator(boolean show);

    /**
     * Envia los parametros de registro capturados en el formulario hacia el presentador
     * @param name Nombre de usuario
     * @param email Email de usuario
     * @param password Password de usuario
     * @param password_confirmation Conirmacion de password
     * @param gender Selección de género
     */
    void signUp(String name, String email, String password,
                String password_confirmation, String gender);

    /**
     * Se cierra la actividad de registro para ir a la actividad principal y muestra un mensaje con el id del nuevo usuario registrado
     * @param id El id del usuario que se registró
     */
    void navigateToMain(int id);

    /**
     * Inicializa el presentador
     */
    void setUpPresenterRegister();
}
