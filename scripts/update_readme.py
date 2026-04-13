import os
import re

def get_readme_info(path):
    if not os.path.exists(path):
        return None, None
    
    with open(path, 'r') as f:
        lines = f.readlines()
        
    title = None
    description = None
    
    for line in lines:
        line = line.strip()
        if not line: continue  # Skip empty lines
        if not title and line.startswith('#'):
            title = line.lstrip('#').strip()
        elif title and not description and not line.startswith('#') and not line.startswith('---'):
            description = line
            break # Stop once both are found
            
    return title, description

def generate_structure():
    base_dir = "."
    # Added common build/system folders to exclude
    exclude = {'.git', '.github', 'scripts', 'target', 'node_modules', 'bin', 'out', '.browserstack'}
    
    structure = ["The project is organized into the following specialized modules:\n"]
    
    # Main category folders
    top_level = ['Live', 'Local-Testing', 'Test-Management']
    
    for folder in top_level:
        if not os.path.isdir(folder):
            continue
            
        readme_name = next((f for f in os.listdir(folder) if f.lower() == "readme.md"), None)
        if readme_name:
            path = os.path.join(folder, readme_name)
            title, desc = get_readme_info(path)
            title = title or folder
            desc = desc or "No description available."
            
            structure.append(f"### [{title}](./{folder})\n{desc}\n- [Documentation](./{path})\n")
            
            # Sub-modules (1 level deep)
            subfolders = sorted([f for f in os.listdir(folder) if os.path.isdir(os.path.join(folder, f)) and f not in exclude])
            for sub in subfolders:
                sub_path = os.path.join(folder, sub)
                sub_readme = next((f for f in os.listdir(sub_path) if f.lower() == "readme.md"), None)
                if sub_readme:
                    sr_path = os.path.join(sub_path, sub_readme)
                    s_title, _ = get_readme_info(sr_path)
                    s_title = s_title or sub
                    structure.append(f"#### [{s_title}](./{sub_path})\n- [Documentation](./{sr_path})\n")

    return "\n".join(structure)

def update_readme():
    readme_path = "Readme.md"
    if not os.path.exists(readme_path):
        return
        
    with open(readme_path, 'r') as f:
        content = f.read()
        
    new_structure = generate_structure()
    
    # Uses flags=re.DOTALL to ensure it captures everything between the tags
    pattern = r".*"
    replacement = f"\n{new_structure}\n"
    
    updated_content = re.sub(pattern, replacement, content, flags=re.DOTALL)
    
    with open(readme_path, 'w') as f:
        f.write(updated_content)

if __name__ == "__main__":
    update_readme()