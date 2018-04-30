package view;

import javax.swing.SwingUtilities;
public class App {

	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			/* (non-Javadoc)
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
