import "../assets/css/container.scss";

import { Button, Input } from "@nextui-org/react";
import { HeartIcon, Smile, SendMsg } from "../assets/icons/icons";
import ChatList from "./container/chatItem";
import { useEffect, useState } from "react";
// websocket
import { io } from "socket.io-client";

function initWebSocketConnect(setGlobalSocket,setuserName) {
  let username = prompt("请输入用户名");

  let socket = io.connect("http://localhost:8888", {
    query: "userName=" + username,
    transports: ["websocket"],
  });

  socket.on("connect", () => {
    setGlobalSocket(socket);
    setuserName(username);

    socket.on("receiveMsg",(data)=>{
      console.log(data);
      
      // console.log(JSON.parse(JSON.stringify(data)));
    })

    socket.on("userOnline",(data)=>{
      console.log(data);
    })

  });


  
}

function msgSendHandler() {}

//获取的聊天记录列表

export default function Container() {

  const [globalSocket, setGlobalSocket] = useState(null);
  const [userName, setuserName] = useState(null);

  //设置全局socket对象
  useEffect(() => {
    initWebSocketConnect(setGlobalSocket,setuserName);
  }, []);

  //输入框内容
  const [msg, setMsg] = useState("");

  //聊天记录
  const [chatList, setChatList] = useState([
    {
      id: 1,
      isMine: false,
      content: "i don't remember anything",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 2,
      isMine: true,
      content:
        " OMG © do you remember what you did last nightat the work night out?",
      sendTime: "18:12",
      sendStauts: true,
    },
  ]);

  // 发送信息
  const sendMsg = () => {
    let sendTime = new Date();

    const newMessage = {
      id: chatList.length + 1,
      isMine: true,
      content: msg,
      sendTime: sendTime.toLocaleTimeString(),
      sendStauts: true,
    };

    globalSocket.emit("sendMsg", {
      msgType: "MS:Group",
      fromUserName: userName, // jiangjing
      content: msg
    });

    setChatList([...chatList, newMessage]); // 更新 chatList 触发组件更新

    setMsg(""); //置空发送消息
  };

  return (
    <>
      <div className="container-box">
        <div className="chat-box">
          {/* 聊天内容 */}
          <div className="content">
            <ChatList chatList={chatList} />
          </div>
          {/* 聊天信息发送框 */}
          <div className="send-msg">
            <div className="send-msg-box">
              {/* 按钮 */}
              <Button isIconOnly color="white" aria-label="Like">
                <Smile className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
              </Button>
              {/* 输入框 */}
              <Input
                type="email"
                value={msg}
                onChange={(e) => {
                  setMsg(e.target.value);
                }}
              />
              {/* 发送按钮 */}
              <Button
                isIconOnly
                color="white"
                aria-label="Like"
                onClick={sendMsg}
              >
                <SendMsg className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
              </Button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
