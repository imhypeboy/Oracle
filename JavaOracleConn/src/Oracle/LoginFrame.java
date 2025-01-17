package Oracle;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginFrame implements ActionListener {
	private Frame f;
	private TextField tfId, tfPwd, tfMsg;
	private Button btn;
	private MemberDAO dao;

	public LoginFrame() {
		dao = new MemberDAO();

		f = new Frame("Login");
		f.setSize(370, 130);
		f.setLocation(500, 200);
		f.setLayout(null);

		Label lid = new Label("ID : ", Label.RIGHT);
		lid.setBounds(10, 30, 100, 20);

		Label lpwd = new Label("Password : ", Label.RIGHT);
		lpwd.setBounds(10, 60, 100, 20);

		tfId = new TextField(10);
		tfId.setBounds(150, 30, 150, 20);

		tfPwd = new TextField(10);
		tfPwd.setEchoChar('*');
		tfPwd.setBounds(150, 60, 150, 20);

		tfMsg = new TextField(10);
		tfMsg.setBounds(10, 90, 340, 20);
		tfMsg.setEditable(false);

		btn = new Button("Login");
		btn.setBounds(300, 30, 50, 50);
		btn.addActionListener(this);

		f.add(lid);
		f.add(lpwd);
		f.add(tfId);
		f.add(tfPwd);
		f.add(tfMsg);
		f.add(btn);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Login")) {
			// id, pwd 값이 입력이 완료되었는지 확인
			String sid = tfId.getText();
			String spwd = tfPwd.getText();

			if (sid.equals("") || spwd.equals("")) {
				tfMsg.setText("아이디와 패스워드를 입력하세요.");
			} else {
				ArrayList<MemberVO> list = dao.list(sid);

				if (list.size() != 0) {
					MemberVO data = (MemberVO) list.get(0);
					String dbid = data.getId();
					String dbpwd = data.getPwd();

					if (sid.equals(dbid) && spwd.equals(dbpwd)) {
						tfMsg.setText("로그인 성공!");
					} else {
						tfMsg.setText("로그인 실패");
					}
				}
			}
		}
	}
}