﻿using System.Net;
using System.Windows;

namespace UI.ManagerWindows
{
    /// <summary>
    /// Interaction logic for AcceptManagerDialog.xaml
    /// </summary>
    public partial class AcceptStationDialog
    {
        public string TypedPassword;
        private readonly UiHandler _ui;
        private readonly IPEndPoint _ip;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="ip">the IP address of the station to accept</param>
        /// <param name="ui">the UIHandler for this UI</param>
        public AcceptStationDialog(IPEndPoint ip, UiHandler ui)
        {
            _ui = ui;
            _ip = ip;
            InitializeComponent();
            StationLabel.Content = ip.Address.ToString();
            Focus();
            PwTextbox.Focus();
            OKButton.IsEnabled = false;
            Title = "Accept Station";
        }

        /// <summary>
        /// Called when the cancel button is clicked
        /// </summary>
        /// <param name="sender">autogenerated</param>
        /// <param name="e">autogenerated</param>
        private void CancelButtonClick(object sender, RoutedEventArgs e)
        {
            DialogResult = false;
            Close();
        }

        /// <summary>
        /// Called when the OK button is clicked
        /// </summary>
        /// <param name="sender">autogenerated</param>
        /// <param name="e">autogenerated</param>
        private void OkButtonClick(object sender, RoutedEventArgs e)
        {
            TypedPassword = PwTextbox.Password;
            DialogResult = true;
            _ui.MarkAsConnected(_ip);
            Close();
        }

        /// <summary>
        /// Called when the text in the PwTextbox changes
        /// </summary>
        /// <param name="sender">autogenerated</param>
        /// <param name="e">autogenerated</param>
        private void PwTextboxPasswordChanged(object sender, RoutedEventArgs e)
        {
            OKButton.IsEnabled = PwTextbox.Password.Length > 0;
        }

    }
}