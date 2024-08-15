<?php

namespace App\Form;

use App\Entity\Process;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class Process1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('id',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('ref',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('etat', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Nouveau' => "Nouveau" ,
            'Supprimé'=>"Supprimé",
            'Modifié' => "Modifié",
            'Inchangé' => "Inchangé" ,
            
        ],
        ])
        ->add('refComposant',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('process',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('criticite', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'Critique' => "Critique" ,
            'Majeure'=>"Majeure",
            'Normale' => "Normale",
            
        ],
        ])
        ->add('messageAlarme',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('intervalleDePolling',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('objet',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('nomTemplate',TextType::class,[
            'attr' => [
                'class' => 'form-control',
            ],
        ])
        ->add('monitoredBy', ChoiceType::class, [
            'attr' => [
                'class' => 'form-select',
            ],
            'choices'  => [
            'OMU' => "OMU" ,
            'Sitescope 1'=>"Sitescope 1",
            'Sitescope 2' => "Sitescope 2" ,
            'NNMI' => "NNMI",
            'RUM' => "RUM" ,
            'BPM' => "BPM" ,
            
        ],
        ])
        
            
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Process::class,
            'attr' => [
                // Ajoutez l'attribut "novalidate" au formulaire
                'novalidate' => 'novalidate',
            ],
        ]);
    }
}
