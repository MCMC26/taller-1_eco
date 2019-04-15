import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class ControlServidor extends Observable implements Runnable {

	// private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private boolean conectado;
	private static InetAddress ip;
	private static ControlServidor ref;

	private ControlServidor() {
		conectado = false;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("Esperando Conexión");
	}

	public static ControlServidor getRef() {
		if (ref == null) {
		
			ref = new ControlServidor();
			Thread t = new Thread(ref);
			t.start();

		}
		return ref;
	}

	
			public void run() {
				try {
					if(!conectado) {
					ServerSocket server= new ServerSocket(5000);
					System.out.println("Esperando cliente");
					Socket socket = server.accept();
					System.out.println("Cliente aceptado");
					entrada = new DataInputStream(socket.getInputStream());
					salida= new DataOutputStream(socket.getOutputStream());
					while(true) {
						recibir();
						Thread.sleep(33);
					}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
			
			}
	private void recibir() throws IOException {
		String[] mensaje = entrada.readUTF().split(": :");
		for (int i=0;i < mensaje.length; i++) {
			mensaje [i]= mensaje[i].trim();
		}
		setChanged();
		notifyObservers(mensaje);
		clearChanged();
	}

	public void enviar(final String id) {
					try {
						salida.writeUTF(id);
						salida.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	public InetAddress getIp() {
		return ip;
	}

}
			