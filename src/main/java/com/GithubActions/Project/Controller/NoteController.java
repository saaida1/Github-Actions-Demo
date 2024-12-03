package com.GithubActions.Project.Controller;



import com.GithubActions.Project.Entity.Note;
import com.GithubActions.Project.Service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/")
    public String showNotes(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("notes", noteService.getAllNotes());
        return "index";
    }

    @PostMapping("/note")
    public String addNote(@Valid @ModelAttribute Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("notes", noteService.getAllNotes());
            return "index";
        }
        noteService.saveNote(note);
        return "redirect:/";
    }

    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/";
    }
}