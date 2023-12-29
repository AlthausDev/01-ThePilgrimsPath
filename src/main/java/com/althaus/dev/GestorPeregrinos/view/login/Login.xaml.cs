using System;
using System.Windows;

namespace Login
{
    /// <summary>
    /// Ventana de inicio de sesión.
    /// </summary>
    public partial class LoginWindow : Window
    {
        /// <summary>
        /// Obtiene o establece el nombre de usuario.
        /// </summary>
        public string Usuario { get; private set; }

        /// <summary>
        /// Obtiene o establece la contraseña.
        /// </summary>
        public string Password { get; private set; }

        /// <summary>
        /// Constructor de la ventana de inicio de sesión.
        /// </summary>
        public LoginWindow()
        {
            InitializeComponent();
            btnLogin.Click += BtnLogin_Click;
            btnClear.Click += BtnClear_Click;
        }

        /// <summary>
        /// Manejador de clic para el botón "Limpiar".
        /// </summary>
        private void BtnClear_Click(object sender, RoutedEventArgs e)        {
            
            txtPassword.Clear();
            txtUsuario.Clear();
        }

        /// <summary>
        /// Manejador de clic para el botón "Iniciar sesión".
        /// </summary>
        private void BtnLogin_Click(object sender, RoutedEventArgs e)
        {
            
            Usuario = txtUsuario.Text;
            Password = txtPassword.Password;

            Console.WriteLine($"{Usuario}");
            Console.WriteLine($"{Password}");
        }
    }
}
