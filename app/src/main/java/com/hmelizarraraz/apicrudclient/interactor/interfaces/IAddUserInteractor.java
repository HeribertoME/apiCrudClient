package com.hmelizarraraz.apicrudclient.interactor.interfaces;

import android.content.Context;

/**
 * Interfaz de modelo interactor
 */
public interface IAddUserInteractor {

    /**
     * Envia los datos al WS
     * @param name Nombre capturado del campo correspondiente en el formulario de registro
     * @param email Email capturado del campo correspondiente en el formulario de registro
     * @param password Password capturado del campo correspondiente en el formulario de registro
     * @param password_confirmation Confirmación de password capturado del campo correspondiente en el formulario de registro
     * @param gender Género capturado del campo correspondiente en el formulario de registro
     * @param context Contexto de la aplicación
     * @param listener Llamada Callback para realizar una acción al presentador
     */
    void register(String name, String email, String password, String password_confirmation,
                String gender, Context context, IMainInteractor.OnUserListener listener);

    /**
     * Valida si el email es válido
     * @param email El email a válidar
     * @return True si es un email correcto. False si no es un email correcto
     */
    boolean isValidEmail(String email);

    /**
     * Valida si el password y la confirmación son correctos
     * @param password El valor del campo password
     * @param password_confirmation El valor del campo confirmar password
     * @return
     */
    boolean isConfirmationPassword(String password, String password_confirmation);
}
