using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Login
{
    public partial class MainWindow : Window
    {

        public string Usuario { get; private set; }
        public string Password { get; private set; }

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            // Utiliza las propiedades en lugar de las variables de clase
            Usuario = txtUsuario.Text;
            Password = txtPassword.Password;

            // Imprime los datos en la salida estándar
            Console.WriteLine($"Usuario: {Usuario}, Contraseña: {Password}");
        }     
    }
}