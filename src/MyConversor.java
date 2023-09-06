import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MyConversor {

	private JFrame frame;
	private JButton btnConvert;
	private JComboBox Box;
	private JTextField text;
	private JLabel lb;

	public enum Moneda {
		pesos_dolar, pesos_euro, pesos_libra, pesos_yen, pesos_won, dolar_pesos, euro_pesos, libra_pesos, yen_pesos,
		won_pesos,
	}

	public double dolar = 4056.33;
	public double euro = 4378.62;
	public double libra = 5124.02;
	public double yen = 27.69;
	public double won = 3.08;

	public double valorInput = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyConversor window = new MyConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Asignacion de valor.
		text = new JTextField();
		text.setBounds(31, 25, 99, 29);
		frame.getContentPane().add(text);
		text.setColumns(10);

		// seccion cambio moneda.
		Box = new JComboBox<Moneda>();
		Box.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		Box.setBounds(31, 106, 99, 37);
		frame.getContentPane().add(Box);

		// seccion boton.
		btnConvert = new JButton("Convert");
		btnConvert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convert();
			}
		});
		btnConvert.setBounds(175, 113, 99, 30);
		frame.getContentPane().add(btnConvert);

		// seccion de resultado.
		lb = new JLabel("0.0");
		lb.setBounds(183, 32, 91, 22);
		frame.getContentPane().add(lb);
	}

	public void Convert() {

		if (Validar(text.getText())) {
			Moneda moneda = (Moneda) Box.getSelectedItem();

			switch (moneda) {
			case pesos_dolar:
				PesosAMoneda(dolar);
				break;

			case pesos_euro:
				PesosAMoneda(euro);
				break;

			case pesos_libra:
				PesosAMoneda(libra);
				break;

			case pesos_yen:
				PesosAMoneda(yen);
				break;

			case pesos_won:
				PesosAMoneda(won);
				break;

			case dolar_pesos:
				MonedaAPesos(dolar);
				break;

			case euro_pesos:
				MonedaAPesos(euro);
				break;

			case libra_pesos:
				MonedaAPesos(libra);
				break;

			case yen_pesos:
				MonedaAPesos(yen);
				break;

			case won_pesos:
				MonedaAPesos(won);
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			}

		}
	}

	public void PesosAMoneda(double moneda) {

		double res = valorInput / moneda;
		lb.setText(Redondear(res));

	}

	public void MonedaAPesos(double moneda) {

		double res = valorInput * moneda;
		lb.setText(Redondear(res));

	}

	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.0");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}

	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0)
				;
			valorInput = x;
			return true;
		} catch (NumberFormatException e) {
			lb.setText("Only Numbers !!");
			return false;
		}
	}

}
