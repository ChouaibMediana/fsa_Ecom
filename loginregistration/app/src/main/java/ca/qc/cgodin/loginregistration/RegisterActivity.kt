package ca.qc.cgodin.loginregistration

import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.qc.cgodin.loginregistration.databinding.ActivityRegistreBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener,View.OnFocusChangeListener,View.OnKeyListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nBinding = ActivityRegistreBinding.inflate(LayoutInflater.from(this))
        setContentView(nBinding.root)  // Make sure you create this layout file
        nBinding.inputUsername.onFocusChangeListener = this
        nBinding.inputEmail.onFocusChangeListener = this
        nBinding.inputPassword.onFocusChangeListener = this
        nBinding.inputConfirmPassword.onFocusChangeListener = this
    }
    private lateinit var nBinding: ActivityRegistreBinding

    private fun validateEmail(): Boolean {
        val value: String = nBinding.inputEmail.text.toString()

        // Check if the email is empty or doesn't contain "@"
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            nBinding.inputEmail.error = "Insert an email or check ur email !!"
            return false // Email is invalid, return false
        }

        return true // Email is valid, return true
    }
    private fun validateUsername(): Boolean {
        val value: String = nBinding.inputUsername.text.toString()
        var errorMessage: String? = null

        // Check if the username is empty
        if (value.isEmpty()) {
            errorMessage = "Username is required!"
        }

        // Apply error message if present
        if (errorMessage != null) {
            nBinding.inputUsername.error = errorMessage
        }

        // Return true if there's no error, false otherwise
        return errorMessage == null
    }

    private fun validatePassword():Boolean{
        val value: String = nBinding.inputPassword.text.toString()

        // Check if the email is empty or doesn't contain "@"
        if (value.isEmpty() ) {
            nBinding.inputEmail.error = "password is required !!"
            return false // Email is invalid, return false
        } else if(value.length<6){
            nBinding.inputEmail.error = "ur password is not Strong enough, it must be at least 6 caracters !!"
        }

        return true

    }
      private fun ConfirmPassword():Boolean{
          var error : String ? = null
          val value : String = nBinding.inputConfirmPassword.text.toString()
          val value1: String = nBinding.inputPassword.text.toString()

          if ( value.isEmpty()){
              error = "Comfirm password is requird !!"
              return false
          } else if (value != value1){
              error = "this password does not match the password above !!"
          }

          return true
      }

    override fun onClick(view: View?) {
        TODO("Not yet implemented")
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
            if(view != null){
                when(view.id){
                    R.id.inputUsername ->{
                        if(hasFocus){
                            if (nBinding.usernameInputLayout.isCounterEnabled){
                                nBinding.usernameInputLayout.isErrorEnabled = true
                            }
                        }else{
                            validateUsername()
                        }
                    }
                    R.id.inputEmail->{
                        if(hasFocus){
                            if (nBinding.emailInputLayout.isCounterEnabled){
                                nBinding.emailInputLayout.isErrorEnabled = true
                            }
                        }else{
                            validateEmail()
                        }
                    }
                    R.id.inputPassword->{
                        if(hasFocus){
                            if (nBinding.passwordInputLayout.isCounterEnabled){
                                nBinding.passwordInputLayout.isErrorEnabled = true
                            }
                        }else{
                            validatePassword()
                        }
                    }
                    R.id.inputConfirmPassword->{
                        if(hasFocus){
                            if (nBinding.confirmPasswordInputLayout.isCounterEnabled){
                                nBinding.confirmPasswordInputLayout.isErrorEnabled = true
                            }
                        }else{
                            ConfirmPassword()
                        }
                    }
                }
            }
    }

    override fun onKey(view: View?, key: Int, Keyevent: KeyEvent?): Boolean {
        return false
    }
}
