import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class ControlServidor extends Observable implements Runnable {
	
	private ServerSocket server;
	private DataOutputStream salida;
	private DataInputStream entrada;
	boolean conectado;
	private String dato;
	private static InetAddress ip;
    private static ControlServidor ref;

	public ControlServidor() {
		conectado = false;
		try {
			ip=InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  public static ControlServidor getRef() { 
	        if(ref == null) {
	            ref = new ControlServidor();
	            Thread t = new Thread(ref);
	            t.start();
	        }
	        return ref;
	    }
	  
	  public static InetAddress getIp() {
		return ip; 	
	    }
	  
			@Override
	public void run() {
				try {
					server= new ServerSocket(5000);
					System.out.println("Esperando cliente");
					Socket socket = server.accept();
					System.out.println("Cliente aceptado");
					entrada = new DataInputStream(socket.getInputStream());
					salida= new DataOutputStream(socket.getOutputStream());
					while(true) {
						entrada();
						Thread.sleep(33);
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			
	private void entrada() throws IOException {
		String[] recibido= entrada.readUTF().split(": :");
		for(int i = 0; i < recibido.length; i++) {
			recibido[i] = recibido[i].trim();
			System.out.println(recibido[i]);
		}
		
		setChanged();
		notifyObservers(recibido);
		clearChanged();
		
	}
	
	public void enviar(String mensaje){
		
		try {
			salida.writeUTF(dato);
			salida.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
		
}
