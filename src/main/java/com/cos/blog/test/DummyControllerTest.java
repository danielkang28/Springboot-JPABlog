package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

// html 파일이 아니라, data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {

    @Autowired      //DummyControllerTest가 메모리에 뜰때 같이 뜨게 해준다. 의존성 주입(DI)
    private UserRepository userRepository;

    // http://localhost:8000/blog/dummy/user
    @GetMapping("dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("dummy/user")
    public List<User> pagelist(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    //{id} 주소로 파라메터를 전달받을 수 있다.
    //http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // findById ->user/4 를 찾았는데, DB에서 못찾아오게 되면 user는 null이 될것인데...
        // 그럼 return null이 되니까.. 프로그램에 문제가 생긴다.
        // Optional로 너의 User 객체를 감싸서 가져올테니, null인지 아닌지 판단해서 return해

        //람다식   // ()-> 추가
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
            }
        });
        // 요청: 웹브라우저
        // user 객체 = 자바 오브젝트
        // 그래서 웹브라우저가 이해할 수 있는 데이터로 변환을 해야한다. json (Gson 라이브러리)
        // 스프링부트 == MessageConverter라는 애가 응답시에 자동으로 작동
        // 만약 자바오브젝트를 리턴하면, MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return user;
    }
    // orElseGet()   <- id 값이 없을 경우, 유저 하나 만들어서 객체에 넣어줘. 그럼 null이 아니잖아
    // orElseThrow() <- id 값이 없을 경우, 예외를 던짐

    //http://localhost:8000/blog/dummy/join (요청)
    //http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")     //회원가입이니까 insert. postmapping
    public String join(User user) {    //key = value 값을 받아줌 (약속된 규칙)
        System.out.println("id : ");
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);    //<- RoleType.java 에서 Enum으로 설정한 값만 넣을 수 있음.
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}