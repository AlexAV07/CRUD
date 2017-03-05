package common.main;

/**
 * Created by Alexander on 27.02.2017.
 */
import common.hibernate.dao.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class IndexController {

    private ManageUser manageUser = new ManageUser();
    private SearchFields searchFields = new SearchFields();
    @RequestMapping("/")
    public String index(ModelMap model)
    {
        return "hello";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "page",required = false) Integer page,
                        ModelMap model)
    {
        if (page!=null) {
            if (page == -1) {
                int prev = manageUser.pageStartNum - manageUser.pageList;
                if (prev < 0) {
                    manageUser.pageStartNum = 0;
                } else {
                    manageUser.pageStartNum = prev;
                }
            } else if (page == 1) {
                int next = manageUser.pageStartNum + manageUser.pageList;
                if (next <= manageUser.pageMax) {
                    manageUser.pageStartNum = next;
                }
            }
        }

        model.addAttribute("searchUser",searchFields);
        List<UserEntity> allUser = manageUser.listUser(searchFields);
        model.addAttribute("listUser",allUser);

        return "hello";
    }

    @RequestMapping("/user_form")
    public String userForm(ModelMap model)
    {
        model.addAttribute("mess","Добавление нового пользователя");
        model.addAttribute("action","user_add");
        model.addAttribute("userAttr",new UserEntity());
        return "user_form";

    }
    @RequestMapping(value = "/user_add", method = RequestMethod.POST)
    public String userAdd(@ModelAttribute("userAttr") UserEntity userEntity,
                          BindingResult result,
                          Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("mess",result.getAllErrors());
            return "error";
        }
        model.addAttribute("allUser",userEntity);
        manageUser.addUser(userEntity);
        return "redirect:hello";

    }
    @RequestMapping(value = "/get_update", method = RequestMethod.GET)
    public String getUp(@RequestParam(value = "id",required = true) Integer id,ModelMap model)
    {
        model.addAttribute("mess","Редактирование данных пользователя");
        model.addAttribute("userAttr",manageUser.getUser(id));
        model.addAttribute("action","user_update?id="+id);

        return "user_form";

    }
    @RequestMapping(value = "/user_update", method = RequestMethod.POST)
    public String userSave(@Valid @ModelAttribute("userAttr") UserEntity userEntity,
                           BindingResult result,
                             @RequestParam(value = "id",required = true) Integer id,
                             Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("mess",result.getAllErrors());
        return "error";
          }
        userEntity.setId(id);
        manageUser.updateUser(userEntity);
        return "redirect:hello";

        //model.addAttribute("allUser",userEntity);
        //return "users";
        //return "redirect:hello";
    }

    @RequestMapping("/users")
    public String userS(ModelMap model)
    {

        return "users";

    }
    @RequestMapping("/search")
    public String userSearch(@ModelAttribute("searchUser") SearchFields search,
                             BindingResult result,
                             ModelMap model)
    {
        if (result.hasErrors()) {
            model.addAttribute("mess", result.getAllErrors());
            return "error";
        }
        searchFields.setSearchName(search.getSearchName());
        searchFields.setSearchAge(search.getSearchAge());
        manageUser.pageStartNum=0;
        return "redirect:hello";
        //return "users";

    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String userDelete(@RequestParam(value = "id",required = true) Integer id,ModelMap model)
    {
        manageUser.deleteUser(id);
        return "redirect:hello";

    }


}
